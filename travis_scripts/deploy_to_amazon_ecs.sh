echo "Launching IN AMAZON ECS"
# ecs-cli configure --region us-west-1 --access-key $AWS_ACCESS_KEY --secret-key $AWS_SECRET_KEY --cluster spmia-tmx-dev
ecs-cli configure profile --profile-name "default" --access-key AKIAIKACZ7FRVAQSHBAQ --secret-key 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
ecs-cli configure --cluster rsp --default-launch-type EC2 --region ap-northeast-2 --config-name default
ecs-cli compose --file docker/aws-dev/docker-compose.yml up
rm -rf ~/.ecs