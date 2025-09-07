#!/bin/bash

echo "🚀 Deploying Allure Reports..."

# Simulated deploy: copy Allure reports to a fake prod folder
mkdir -p /tmp/prod-allure
cp -r allure-report-* /tmp/prod-allure/

echo "✅ Deployment complete."
