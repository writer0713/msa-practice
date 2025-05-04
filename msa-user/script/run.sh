#!/bin/bash

GRADLEW=../gradlew

echo "Starting Spring Boot application using $GRADLEW bootRun..."

# gradlew 스크립트에 실행 권한이 있는지 확인하고 실행
if [ ! -x $GRADLEW ]; then
  echo "Error: gradlew script not found or is not executable."
  exit 1
fi
"$GRADLEW" bootRun

# 스크립트 실행 결과 확인 (선택 사항)
exit_code=$?
if [ $exit_code -ne 0 ]; then
  echo "Error: bootRun task failed with exit code $exit_code"
  exit $exit_code
else
  echo "bootRun finished."
fi

exit 0