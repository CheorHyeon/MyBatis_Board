# MyBatis_Board

## 프로젝트 소개  
이 프로젝트는 **MyBatis**를 사용하여 간단한 게시판(CRUD) API를 구현한 예제입니다.  
- Java + Gradle 기반  
- MyBatis XML Mapper 방식으로 SQL과 자바 객체를 매핑
- 게시글 생성, 조회, 수정, 삭제 기능 포함

## 목적  
- MyBatis의 기본 사용법과 설정 방식을 익히기 위함  
- 실제 CRUD API를 통해 MyBatis 흐름(설정 → SqlSession → Mapper)을 경험  

## MyBatis 사용 방법

### SqlSessionFactory 설정
- Spring Boot 사용 시 spring-boot-starter-mybat​is 의존성 추가하면 자동 설정
- 직접 설정하는 방식은 패스

### yml 파일에서 mapper로 사용할 xml 파일 경로 지정

```yaml
mybatis:
  # Mapper의 위치 지정, resources/mapper/a.xml, b.xml...
  mapper-locations: classpath:mapper/*.xml # 쿼리문, xml 형태 / interface or class로도 작성 하기도 함
  # 설정 파일 지정 : resources 하위 바로 위치
  config-location: classpath:mybatis-config.xml # 설정 파일 지정
```

### mapper-locations와 config-location의 차이점
- mapper-locations : 실제 SQL 구문이 적힌 Mapper XML 파일(.xml)들의 경로 지정
  - 실제 SQL 매핑 파일
- config-location : MyBatis의 전체 동작 설정 파일(mybatis-config.xml) 위치 지정
  - MyBatis 전체 설정

### mybatis-config.xml
- typeAlias 태그로 xml 태그 안에서 parameterType이나 resultType에 사용할 DTO 파일을 간단하게 사용할 수 있도록 별칭을 지정할 수 있다.
  - ex) `hello.mybatispractice.boad.dto.BoardDTO` DTO를 `board` 로 축약해서 mapper에서 사용할 수 있음
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <!-- type에 경로 확인! -->
        <typeAlias type="hello.mybatispractice.boad.dto.BoardDTO" alias="board"></typeAlias>
    </typeAliases>
</configuration>
```

### board-mapper.xml
- sql 쿼리를 지정
  - namespace와 id로 java 코드에서 해당 mapper와 매칭하여 쿼리 실행
- parameterType은 파라미터로 받는 것이고, 여기서는 config 파일에서 typeAlias 태그로 지정한 별칭을 사용할 수도 있다. (기본 자료형도 됨)
- resultType은 반환 타입을 정의한 것이고, 여기서는 config 파일에서 typeAlias 태그로 지정한 별칭을 사용할 수도 있다. (기본 자료형도 됨)
  - 만약 한 row 전체를 반환(select * ...)를 하고 resultType이 Dto Class라면?
    - MyBatis 자체의 Reflection 기반 매핑이 이뤄짐
      - 런타임 시 빈 객체 생성 + setter 호출
```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="Board">
    <!-- parameterType은 mybatis-config.xml에서 typeAliases 태그로 적어둔 것만 사용 가능 -->
    <!-- 그렇지 않으면 dto 파일의 패키지 전체 경로를 적어야 한다 -->
    <insert id = "save" parameterType="board">
        insert into board_table(boardTitle, boardWriter, boardPass, boardContents)
        values(
                  #{boardTitle}, #{boardWriter}, #{boardPass}, #{boardContents}
              )
    </insert>

    <!-- select 경우 resultType은 보통 있고, parameterType은 선택-->
    <select id = "findAll" resultType="board">
        select * from board_table order by id desc
    </select>

    <!-- parameterType은 기본 자료형도 가능하다 -->
    <update id = "updateHits" parameterType="Long">
        update board_table set boardHits = boardHits + 1 where id = #{id}
    </update>

    <select id = "findById" parameterType="Long" resultType="board">
        select * from board_table where id = #{id}
    </select>

    <update id = "update" parameterType="board">
        update board_table set boardTitle = #{boardTitle}, boardContents = #{boardContents} where id = #{id}
    </update>

    <delete id="delete" parameterType="Long">
        delete from board_table where id = #{id}
    </delete>
</mapper>
```

## 실행 전 수행
- DB 생성 및 yml datasource 정보 매핑
- SQL Table 생성

```sql
use matis;

-- board_table
drop table if exists board_table;
create table board_table
(
    id bigint primary key auto_increment,
    boardTitle varchar(255),
    boardWriter varchar(255),
    boardPass varchar(255),
    boardContents varchar(500),
    boardHits int default 0,
    createdAt datetime default now(),
    fileAttached int default 0
);
-- board_file_table
drop table if exists board_file_table;
create table board_file_table
(
    id	bigint auto_increment primary key,
    originalFileName varchar(255),
    storedFileName varchar(255),
    boardId bigint
);
```

## 출처
- [스프링부트 mybatis 게시판 프로젝트]([https://www.youtube.com/watch?v=8x8jZgGYt0k&list=PLV9zd3otBRt6n2MYMuYtOucBcJVH_TO5C&index=1](https://www.youtube.com/playlist?list=PLV9zd3otBRt6n2MYMuYtOucBcJVH_TO5C))
