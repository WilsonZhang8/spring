package com.zghw.spring.demo.demo.annontaion.app.repository;

import org.springframework.stereotype.Repository;
/**
 * 使用@Repository被注解的类经常被代表是一个“数据访问层”(例如DAO),
 * 当使用自动扫描classpath下的类时，会自动的把被@Repository注解过的类加入到候选类中。
 * 它是作为一个专业化的 @Component组件
 * @author zghw
 *
 */
@Repository
public class MyRepositoryImpl implements MyRepository{

}
