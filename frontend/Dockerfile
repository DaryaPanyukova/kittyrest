FROM node:current-alpine as builder

COPY . /app
WORKDIR /app
RUN npm install
WORKDIR /app/src
RUN npm run build

FROM node:current-alpine
COPY --from=builder /app/build /app
RUN npm install -g serve
WORKDIR /app
EXPOSE 3000
CMD [ "serve", "-s", "." ]
