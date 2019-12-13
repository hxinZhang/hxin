package ${modelPath};

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * ${modelName}
 * @author
 */
@Data
@ToString
public class ${modelName} extends BaseEntity<${modelName}> implements Serializable{

<#if fieldMap??>
    <#list fieldMap?keys as key>
     //${remarkColumnMap[key]}
     private ${fieldMap[key]} ${key};
    </#list>
</#if>

}
