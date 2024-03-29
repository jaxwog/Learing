package com.love.jax.database.update;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CreateVersion
 * @Description: 数据库升级创建表脚本
 * @date 2012-12-6 下午1:46:57
 */
public class CreateVersion {
    /**
     * 版本信息
     */
    private String version;

    /**
     * 创建数据库表脚本
     */
    private List<CreateDb> createDbs;

    /**
     * <createVersion version="V003">
     *         <createDb name="user">
     *             <!-- 设备与软件关联信息 -->
     *             <sql_createTable>
     *                 create table if not exists tb_login(
     *                 name TEXT,
     *                 password TEXT,
     *                 loginName TEXT,
     *                 lastLoginTime,
     *                 user_id Integer primary key
     *                 );
     *             </sql_createTable>
     *         </createDb>
     *     </createVersion>
     */
    public CreateVersion(Element ele) {
        version = ele.getAttribute("version");

        {
            createDbs = new ArrayList<CreateDb>();
            NodeList cs = ele.getElementsByTagName("createDb");
            for (int i = 0; i < cs.getLength(); i++) {
                Element ci = (Element) (cs.item(i));
                CreateDb cd = new CreateDb(ci);
                this.createDbs.add(cd);
            }
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<CreateDb> getCreateDbs() {
        return createDbs;
    }

    public void setCreateDbs(List<CreateDb> createDbs) {
        this.createDbs = createDbs;
    }

}
