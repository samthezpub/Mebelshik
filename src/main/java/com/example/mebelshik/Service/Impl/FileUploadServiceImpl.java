package com.example.mebelshik.Service.Impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;
import com.example.mebelshik.Config.S3Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl {
    private static final String UPLOAD_DIR = "/uploads/";
    private static final String CLIENT_UPLOAD_DIR = "resources/uploads/";


    public String saveFileForProductByProductSlugAndIterator(MultipartFile file, String slug, Integer iterator) throws IOException {
        // Параметры подключения к S3
        String bucketName = S3Configuration.getBucketName(); // Имя вашего бакета
        String accessKey = S3Configuration.getAccessKey(); // Ваш Access Key
        String secretKey = S3Configuration.getSecretKey(); // Ваш Secret Access Key
        String region = S3Configuration.getRegion(); // Регион (можно не использовать для Timeweb)

        // Создаем учетные данные
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://s3.timeweb.cloud", region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withPathStyleAccessEnabled(true) // Включаем path-style
                .build();

        // Имя файла в S3
        String keyName = slug + "_" + iterator + ".jpg";

        // Устанавливаем метаданные
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType("image/jpeg"); // Укажите тип контента, если известен

        // Загружаем файл в S3
        try {
            // Создаем запрос на загрузку
            PutObjectRequest request = new PutObjectRequest(bucketName, keyName, file.getInputStream(), metadata);
            s3Client.putObject(request);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Ошибка при загрузке файла в S3", e);
        }

        // Возвращаем URL загруженного файла
        return String.format("https://s3.timeweb.cloud/%s/%s", bucketName, keyName);
    }

}
