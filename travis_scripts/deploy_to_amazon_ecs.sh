echo "Launching IN AMAZON ECS"
# ecs-cli configure --region us-west-1 --access-key $AWS_ACCESS_KEY --secret-key $AWS_SECRET_KEY --cluster spmia-tmx-dev
ecs-cli configure profile --profile-name "default" --access-key AKIAJ7347PDJA65UD6YQ --secret-key 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
ecs-cli configure --cluster rsp --default-launch-type EC2 --region ap-northeast-2 --config-name default
ecs-cli compose --file docker/aws-dev/docker-compose.yml up
rm -rf ~/.ecs