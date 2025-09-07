#!/bin/bash

echo "⏪ Rolling back..."

# Simulated rollback: delete the failed deploy
rm -rf /tmp/prod-allure

echo "✅ Rollback complete."
