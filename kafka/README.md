# Docker Compose 파일
- zookeeper

| 환경 변수| 설명                                                                                                                                                                                                                                                                                          |
|-------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ZOOKEEPER_SERVER_ID | - zookeeper 클러스터에서 유일하게 주키퍼를 식별할 아이디이다. <br> - 동일 클러스터 내에서 이 값은 중복되면 안된다. 단일 브로커이기 때문에 이 값은 의미가 없다.                                                                                                                                                                                         |
| ZOOKEEPER_CLIENT_PORT | - zookeeper_client_port를 지정한다. 여기서는 기본 주키퍼의 포트인 2181로 지정한다. <br> - 즉 컨테이너 내부에서 주키퍼는 2181로 실행된다.                                                                                                                                                                                             |
| ZOOKEEPER_TICK_TIME | - zookeeper가 클러스터를 구성할때 동기화를 위한 기본 틱 타임을 지정한다. <br> - 여기서는 2000ms로 지정한다.                                                                                                                                                                                                                    |
| ZOOKEEPER_INIT_LIMIT | - zookeeper 초기화를 위한 제한 시간을 설정한다. <br> - 주키퍼 클러스터는 쿼럼이라는 과정을 통해서 마스터를 선출하게 된다. 이때 주키퍼들이 리더에게 커넥션을 맺을때 지정할 초기 타임아웃 시간이다. <br> - 타임아웃 시간은 이전에 지정한 ZOOKEEPER_TICK_TIME 단위로 설정된다. <br> - ZOOKEEPER_TICK_TIME을 2000으로 지정했고, ZOOKEEPER_INIT_LIMIT을 5로 잡았으므로 2000 * 5 = 10000 밀리세컨이 된다. 즉, 10초가 된다. |
| ZOOKEEPER_SYNC_LIMIT | - zookeeper의 동기화 시간을 지정한다. <br> - 여기서는 5로 지정한다.                                                                                                                                                                                                                                             |

- kafka

| 환경 변수| 설명                                                                        |
|-------|---------------------------------------------------------------------------|
| KAFKA_BROKER_ID | kafka 브로커 아이디를 지정한다. 유니크해야하며 지금 예제는 단일 브로커기 때문에 없어도 무방하다. |
| KAFKA_ZOOKEEPER_CONNECT | kafka가 zookeeper에 커넥션하기 위한 대상을 지정한다. |
| KAFKA_ADVERTISED_LISTENERS | 외부에서 접속하기 위한 리스너 설정을 한다. |
| KAFKA_LISTENER_SECURITY_PROTOCOL_MAP | 보안을 위한 프로토콜 매핑이디. 이 설정값은 KAFKA_ADVERTISED_LISTENERS 과 함께 key/value로 매핑된다. |
| KAFKA_INTER_BROKER_LISTENER_NAME | 도커 내부에서 사용할 리스너 이름을 지정한다. <br> 이전에 매핑된 PLAINTEXT가 사용되었다.  |
| KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR | single 브로커인경우에 지정하여 1로 설정했다. <br> 멀티 브로커는 기본값을 사용하므로 이 설정이 필요 없다. |
| KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS | 카프카 그룹이 초기 리밸런싱할때 컨슈머들이 컨슈머 그룹에 조인할때 대기 시간이다. |


# Docker Compose 실행
```bash
$ docker-compose -f docker-compose-single.yml up -d

[+] Running 3/3
 ⠿ Network handson_01_default        Created                                             0.0s
 ⠿ Container handson_01-zookeeper-1  Started                                             0.9s
 ⠿ Container handson_01-kafka-1      Started                                             1.7s
```