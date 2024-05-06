# Fetching the latest node image on apline linux
FROM node:alpine AS builder

# Declaring env
ENV NODE_ENV production

# Setting up the work directory
WORKDIR /app

# Installing dependencies
COPY ./package.json ./
COPY ./package-lock.json ./
RUN npm install

# Install additional dependencies
RUN npx update-browserslist-db@latest
RUN npm install --save-dev @babel/plugin-proposal-private-property-in-object
# Copying all the files in our project
COPY . . 
RUN npm install -g npm-check-updates && ncu -u && npm install


# Building our application
RUN npm run build

# Fetching the latest nginx image
FROM nginx

# Copying built assets from builder
COPY --from=builder /app/build /usr/share/nginx/html

# Copying our nginx.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf
