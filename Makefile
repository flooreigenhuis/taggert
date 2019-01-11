
SOURCE_PATH := src/main

PROTOC := /usr/local/bin/protoc

MODULE := taggert/v1b1

JAVA_OUT := target/generated-sources/protobuf/java
PROTO_SELF := $(SOURCE_PATH)/proto
GOOGLE_APIS := /home/hayo/Documents/googleapis

clean:
	mvn clean

protoc:
	mkdir -p $(JAVA_OUT)
	$(PROTOC) --java_out=$(JAVA_OUT) \
		-I$(GOOGLE_APIS) \
		-I$(PROTO_SELF) \
		src/main/proto/incentro/$(MODULE)/*.proto

compile:
	mvn compile

test: clean
	mvn verify

run:
	mvn appengine:run