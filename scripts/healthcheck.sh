#!/bin/bash

echo "🩺 Running health check..."

# Simulate a health check by checking if report folder exists
if [ -d /tmp/prod-allure ]; then
  echo "✅ Health check passed!"
  exit 0
else
  echo "❌ Health check failed!"
  exit 1
fi

