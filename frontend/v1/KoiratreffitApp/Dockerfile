FROM node:16

# Set the working directory inside the container
WORKDIR /koiratreffit/frontend

# Copy package jsons
COPY package*.json ./

# install dependencies
RUN npm install

# copy app code
COPY . .

# Expose the port the app runs on
EXPOSE 8081

# Define the command to run the application
CMD ["npm", "start"]