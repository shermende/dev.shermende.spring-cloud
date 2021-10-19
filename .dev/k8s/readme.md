## access to resources: configmaps. 403 issue

https://stackoverflow.com/questions/55498702/how-to-fix-forbiddenconfigured-service-account-doesnt-have-access-with-spark

### configure namespaces

##### run cluster-role.yml before

kubectl create namespace env-authorization kubectl create clusterrolebinding
microservices-kubernetes-namespace-reader-env-authorization --clusterrole=microservices-kubernetes-namespace-reader
--serviceaccount=env-authorization:default

kubectl create namespace app-reference kubectl create clusterrolebinding
microservices-kubernetes-namespace-reader-app-reference --clusterrole=microservices-kubernetes-namespace-reader
--serviceaccount=app-reference:default

kubectl create namespace app-game kubectl create clusterrolebinding microservices-kubernetes-namespace-reader-app-game
--clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=app-game:default

kubectl create namespace env-sba kubectl create clusterrolebinding microservices-kubernetes-namespace-reader-env-sba
--clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=env-sba:default

kubectl create namespace env-kafka kubectl create clusterrolebinding microservices-kubernetes-namespace-reader-env-kafka
--clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=env-kafka:default

###### # remove none images

docker rmi $(docker images --filter "dangling=true" -q --no-trunc)
