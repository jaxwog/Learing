package com.love.jax.database.update;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UpdateDbXml
 * @Description: 升级更新数据库
 * @date 2012-12-6 下午1:37:47
 */
public class UpdateDbXml {
    /**
     * 升级脚本列表
     */
    private List<UpdateStep> updateSteps;

    /**
     * 升级版本
     */
    private List<CreateVersion> createVersions;

    /**
     *<updateXml>
     *     <createVersion version="V003">
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
     *     <updateStep
     *         versionFrom="V002"
     *         versionTo="V003">
     *         <updateDb name="user">
     *             <sql_before>alter table tb_login rename to bak_t_login;</sql_before>
     *             <sql_after>
     *                 insert into tb_login(name,
     *                 password)
     *                 select name,password
     *                 from bak_tb_login;
     *             </sql_after>
     *             <sql_after>
     *                 drop table if exists bak_t_login;
     *             </sql_after>
     *         </updateDb>
     *     </updateStep>
     *
     * </updateXml>
     */

    public UpdateDbXml(Document document) {
        {
            // 获取升级脚本
            NodeList updateSteps = document.getElementsByTagName("updateStep");
            this.updateSteps = new ArrayList<UpdateStep>();
            for (int i = 0; i < updateSteps.getLength(); i++) {
                Element ele = (Element) (updateSteps.item(i));
                UpdateStep step = new UpdateStep(ele);
                this.updateSteps.add(step);
            }
        }
        {
            /**
             * 获取各升级版本
             */
            NodeList createVersions = document.getElementsByTagName("createVersion");
            this.createVersions = new ArrayList<CreateVersion>();
            for (int i = 0; i < createVersions.getLength(); i++) {
                Element ele = (Element) (createVersions.item(i));
                CreateVersion cv = new CreateVersion(ele);
                this.createVersions.add(cv);
            }
        }
    }

    public List<UpdateStep> getUpdateSteps() {
        return updateSteps;
    }

    public void setUpdateSteps(List<UpdateStep> updateSteps) {
        this.updateSteps = updateSteps;
    }

    public List<CreateVersion> getCreateVersions() {
        return createVersions;
    }

    public void setCreateVersions(List<CreateVersion> createVersions) {
        this.createVersions = createVersions;
    }

}
