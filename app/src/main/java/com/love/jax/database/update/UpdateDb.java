package com.love.jax.database.update;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UpdateDb
 * @Description: 更新数据库脚本
 * @date 2012-12-6 下午1:52:34
 */
public class UpdateDb {
    /**
     * 数据库名称
     */
    private String dbName;

    /**
     *
     */
    private List<String> sqlBefores;

    /**
     *
     */
    private List<String> sqlAfters;

    /**
     *  <updateDb name="logic">
     *             <sql_before>alter table tb_photo rename to bak_tb_photo;</sql_before>
     *             <sql_after>
     *                 insert into tb_photo(time,
     *                 path)
     *                 select time,path
     *                 from bak_tb_photo;
     *             </sql_after>
     *             <sql_after>
     *                 drop table if exists bak_tb_photo;
     *             </sql_after>
     *         </updateDb>
     */

    public UpdateDb(Element ele) {
        dbName = ele.getAttribute("name");
        sqlBefores = new ArrayList<String>();
        sqlAfters = new ArrayList<String>();

        {
            NodeList sqls = ele.getElementsByTagName("sql_before");
            for (int i = 0; i < sqls.getLength(); i++) {
                String sql_before = sqls.item(i).getTextContent();
                this.sqlBefores.add(sql_before);
            }
        }

        {
            NodeList sqls = ele.getElementsByTagName("sql_after");
            for (int i = 0; i < sqls.getLength(); i++) {
                String sql_after = sqls.item(i).getTextContent();
                this.sqlAfters.add(sql_after);
            }
        }

    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<String> getSqlBefores() {
        return sqlBefores;
    }

    public void setSqlBefores(List<String> sqlBefores) {
        this.sqlBefores = sqlBefores;
    }

    public List<String> getSqlAfters() {
        return sqlAfters;
    }

    public void setSqlAfters(List<String> sqlAfters) {
        this.sqlAfters = sqlAfters;
    }
}
