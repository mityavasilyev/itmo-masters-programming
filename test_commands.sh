#!/bin/bash

# Function to send a command to the server and check the response
test_command() {
    local command=$1
    local expected_response=$2
    echo "Sending command: $command"
    # Use nc with a timeout of 5 seconds
    response=$(echo "$command" | nc -w 5 localhost 1234)
    if [[ "$response" == "$expected_response" ]]; then
        echo "Test passed: $command"
    else
        echo "Test failed: $command. Expected: $expected_response, Got: $response"
        exit 1
    fi
}

# Test commands
test_command "store key1 value1" "Значение сохранено"
test_command "load key1" "value1"
test_command "search key" "key1"
test_command "check key1 value1" "Проверка прошла успешно"
test_command "put key2 value2" "Ключ и значение сохранены"
test_command "get key2" "value2"

echo "All tests passed."
