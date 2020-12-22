#### [问题记录1]
添加功能涉及到添加一个数据库表：
1. 在数据库中新建数据库表。
2. MsgInterfaceCheck：在src\com\jeecms\core\entity目录创建实现接口的（implements Serializable）数据实体类。
3. MsgInterfaceCheck.hbm.xml：在src\com\jeecms\core\entity\hbm\oracle目录创建*.hbm.xml数据库映射文件，注意数据库表映射、字段映射。
4. MsgInterfaceCheckDao、MsgInterfaceCheckDaoImpl：在src\com\jeecms\core\dao（impl）目录创建接口类(接口实现类：类前添加@Repository,类前添加：extends HibernateBaseDao<实体类名, Integer> implements MsgInterfaceCheckDao )
5. MsgInterfaceCheckMng、MsgInterfaceCheckMngImpl：在src\com\jeecms\core\manager（impl）目录创建接口类(接口实现类：类前添加@Service,@Transactional)
6. 在配置文件jeecore-context.xml里面添加
<bean id="msgInterfaceCheckDao" class="com.jeecms.core.dao.impl.MsgInterfaceCheckDaoImpl"/>
<bean id="msgInterfaceCheckMng" class="com.jeecms.core.manager.impl.MsgInterfaceCheckMngImpl"/>
#### [1结束]

#### [问题记录2]
用户登录问题：
用户登录使用shiro,再类CmsAuthenticationFilter里面进行用户判断。
最终结果在MemberAct.java里面进行修改。
shiro-context.xml里面配置需要登录校验的页面。
#### [2结束]

#### [:fa-rocket: MyClouds在线演示](http://118.126.108.44)=======
#### [:fa-book: **MyClouds开发文档** ](https://gitee.com/osworks/MyClouds/wikis/pages) &nbsp;&nbsp;&nbsp;&nbsp; [:fa-rocket: **MyClouds在线演示**](http://118.126.108.44) &nbsp;&nbsp;&nbsp;&nbsp; [ :fa-flask: **MyClouds代码生成器**](https://gitee.com/osworks/Myclouds-Builder)
