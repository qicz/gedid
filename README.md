# Gedid 
Distributed id generator.

![maven](https://img.shields.io/maven-central/v/cn.zhucongqi/gedid.svg)

# features

- distributed;
- orderly;
- thread safety.

# How to use?

- Step 1: init DidLoader

```java
DidLoader loader = DidLoader.init(GeneratorConfig.defaultRedisConfig());
```

- Step2: follow business with name

```java
DidGenerator user = loader.follow("user");
```

- Step3: get business id

```java
Long currentId = user.current();
Long nextId = user.next();
```

or

```java
Long currentId = loader.current("user");
Long nextId = loader.next("user");
```

# TODO

support zookeeper