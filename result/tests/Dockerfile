FROM node:14-slim

# Update the package repositories and install required packages
RUN apt-get update -qq && apt-get install -qy \
    ca-certificates \
    bzip2 \
    curl \
    libfontconfig \
    --no-install-recommends

# Install PhantomJS
RUN yarn global add phantomjs-prebuilt

# Copy your application code into the container
ADD . /app

# Set the working directory
WORKDIR /app

# Define the command to run when the container starts
CMD ["/app/tests.sh"]
