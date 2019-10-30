echo "Launching IN AMAZON ECS"
#ecs-cli configure --region ap-northeast-2 --access-key AKIAJ7DCR5V7MYVWTOMA --secret-key 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 --cluster rsp
ecs-cli configure profile --profile-name "default" --access-key AKIAJEWR4GL4R5S4GG6Q --secret-key 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
ecs-cli configure --cluster rsp --default-launch-type EC2 --region ap-northeast-2 --config-name default
ecs-cli compose --file docker/aws-dev/docker-compose.yml up
rm -rf ~/.ecs