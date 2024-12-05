# Variables
SRC_DIR = ./src
BUILD_DIR = ./build
MAIN_CLASS = main.Main
JAR_FILE = $(BUILD_DIR)/app.jar

# Find all .java files in the SRC_DIR
SOURCES = $(shell find $(SRC_DIR) -name '*.java')

# Default target
all: $(JAR_FILE)

# Compile all .java files
$(JAR_FILE): $(SOURCES)
	mkdir -p $(BUILD_DIR)
	javac -d $(BUILD_DIR) -sourcepath $(SRC_DIR) $(SOURCES)
	jar cfe $(JAR_FILE) $(MAIN_CLASS) -C $(BUILD_DIR) .

# Run the application
run: $(JAR_FILE)
	java -jar $(JAR_FILE)

solo: $(JAR_FILE)
	git checkout main
	make run 
# Clean build artifacts
clean:
	rm -rf $(BUILD_DIR)

.PHONY: all run clean
