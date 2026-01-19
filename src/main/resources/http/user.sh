#!/bin/bash

BASE_URL="http://localhost:8080"
TIMESTAMP=$(date +%s)
EMAIL="test${TIMESTAMP}@example.com"

echo "=== 회원가입 (email: $EMAIL) ==="
RESPONSE=$(curl -s -X POST "$BASE_URL/api/users/signup" \
  -H "Content-Type: application/json" \
  -d "{
    \"email\": \"$EMAIL\",
    \"password\": \"password123\",
    \"nickname\": \"테스트유저\"
  }")
echo "$RESPONSE"
USER_ID=$(echo "$RESPONSE" | grep -o '"id":[0-9]*' | grep -o '[0-9]*')
echo -e "\n"

echo "=== 사용자 단건 조회 (ID: $USER_ID) ==="
curl -s -X GET "$BASE_URL/api/users/$USER_ID"
echo -e "\n"

echo "=== 전체 사용자 조회 ==="
curl -s -X GET "$BASE_URL/api/users"
echo -e "\n"

echo "=== 회원정보 수정 (ID: $USER_ID) ==="
curl -s -X PUT "$BASE_URL/api/users/$USER_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "nickname": "수정된닉네임",
    "password": "newpassword123"
  }'
echo -e "\n"

echo "=== 회원 삭제 (ID: $USER_ID) ==="
curl -s -X DELETE "$BASE_URL/api/users/$USER_ID"
echo -e "\n"

echo "=== 삭제 확인 (전체 사용자 조회) ==="
curl -s -X GET "$BASE_URL/api/users"
echo -e "\n"
