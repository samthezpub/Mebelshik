# Stage 1: Build the application
FROM node:18-alpine AS builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем package.json и package-lock.json из src в корневую директорию контейнера
COPY ./package.json ./package-lock.json ./

RUN ls -la /app
# Устанавливаем зависимости
RUN npm install

# Копируем весь код из директории src в контейнер
COPY src ./
COPY public ./

# Сборка приложения
RUN npm run build

# Stage 2: Production image
FROM node:18-alpine AS runner

WORKDIR /app

ENV NODE_ENV=production
ENV NEXT_TELEMETRY_DISABLED=1
ENV HOSTNAME="0.0.0.0"

# Создаем группу и пользователя
RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 nextjs

# Копируем необходимые файлы из builder stage
COPY --from=builder /app/ ./

# Устанавливаем права на .next директорию
RUN chown -R nextjs:nodejs .next

USER nextjs

EXPOSE 3000

# Запуск приложения через встроенный сервер Next.js
CMD ["npm", "run", "start"]
