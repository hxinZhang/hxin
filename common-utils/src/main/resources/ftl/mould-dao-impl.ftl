package ${daoPath}.impl;

import ${daoPath}.${daoName};
import ${modelPath}.${modelName};
import ${mapperPath}.${mapperName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * ${daoName}Impl
 * @author
 */
@Repository
public class ${daoName}Impl implements ${daoName} {

    @Autowired
    private ${mapperName} mapper;

    /**
     * 分页 查询
     * @param t
     * @return
     * @throws SQLException
     */
    @Override
    public List<${modelName}> queryByPage(${modelName} t) throws SQLException{
        return mapper.queryByPage(t);
    }

    /**
     * 分页 统计
     * @param t
     * @return
     * @throws SQLException
     */
    @Override
    public Integer countByPage(${modelName} t) throws SQLException{
       return mapper.countByPage(t);
    }

    /**
     * 通过id查询实体类信息
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public ${modelName} queryById(Integer id) throws SQLException {
        ${modelName} t = new ${modelName}();
        t.setId(id);
        return mapper.get(t);
    }

    /**
     * 通过实体查询实体类信息
     * @param t
     * @return
     * @throws SQLException
     */
    @Override
    public ${modelName} queryByModel(${modelName} t) throws SQLException {
        return mapper.get(t);
    }

    /**
     * 新增
     * @param t
     * @return
     * @throws SQLException
     */
    @Override
    public Integer insert(${modelName} t) throws SQLException {
        Integer num = mapper.insert(t);
        if(num==1){
            return t.getId();
        }
        return null;
    }

    /**
     * 修改
     * @param t
     * @return
     * @throws SQLException
     */
    @Override
    public Integer modify(${modelName} t) throws SQLException {
        return mapper.update(t);
    }

    /**
     * 删除
     * @param ids
     * @return
     * @throws SQLException
     */
    @Override
    public Integer delete(List ids) throws SQLException {
        return mapper.batchDel(ids);
    }
    
}