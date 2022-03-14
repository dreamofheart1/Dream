package com.gttss.rfidlibrary.service.${packageName};

import com.gttss.entity.PageData;
import com.gttss.rfidlibrary.dao.${packageName}.${typeName}Dao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Error.class})
public class ${typeName}Service {

    @Resource
    private ${typeName}Dao ${typeNameLower}Dao;

    /**
    * @Title
    * @Description
    * @param
    * @return
    * @throws
    * @author ${authorName}
    * @date ${nowDate?string("yyyy-MM-dd HH:mm:ss")}
    */
    public int findCount(){return ${typeNameLower}Dao.findCount();}
    /**
    * @Title
    * @Description
    * @param pd
    * @return
    * @throws
    * @author ${authorName}
    * @date ${nowDate?string("yyyy-MM-dd HH:mm:ss")}
    */
    public List<PageData> findList(PageData pd){
        return ${typeNameLower}Dao.findList(pd);
    }
    /**
    * @Title
    * @Description
    * @param pd
    * @return
    * @throws
    * @author ${authorName}
    * @date ${nowDate?string("yyyy-MM-dd HH:mm:ss")}
    */
    public PageData findByUuid(PageData pd){
        return ${typeNameLower}Dao.findByUuid(pd);
    }
    /**
    * @Title
    * @Description
    * @param pd
    * @return
    * @throws
    * @author ${authorName}
    * @date ${nowDate?string("yyyy-MM-dd HH:mm:ss")}
    */
    public int add(PageData pd){
        return ${typeNameLower}Dao.add(pd);
    }
    /**
    * @Title
    * @Description
    * @param pd
    * @return
    * @throws
    * @author ${authorName}
    * @date ${nowDate?string("yyyy-MM-dd HH:mm:ss")}
    */
    public int edit(PageData pd){
        return ${typeNameLower}Dao.edit(pd);
    }
    /**
    * @Title
    * @Description
    * @param pd
    * @return
    * @throws
    * @author ${authorName}
    * @date ${nowDate?string("yyyy-MM-dd HH:mm:ss")}
    */
    public int delete(PageData pd){
        return ${typeNameLower}Dao.delete(pd);
    }
    /**
    * @Title
    * @Description
    * @param
    * @return
    * @throws
    * @author ${authorName}
    * @date ${nowDate?string("yyyy-MM-dd HH:mm:ss")}
    */
    public List<PageData> dataListPage(){return ${typeNameLower}Dao.dataListPage(); }
    /**
    * @Title
    * @Description
    * @param
    * @return
    * @throws
    * @author ${authorName}
    * @date ${nowDate?string("yyyy-MM-dd HH:mm:ss")}
    */
    public List<PageData> listAll(){return ${typeNameLower}Dao.listAll(); }


}