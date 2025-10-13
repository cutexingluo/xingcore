package top.cutexingluo.core.utils.se.file.pkg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XingTian
 * @version 1.0.0
 * @date 2025/10/13 15:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XTFile implements XTFileSource{

    /**
     * 原始名
     */
    protected String originalFilename; // 原始名

    /**
     * 唯一 id
     */
    protected String uuid; // 唯一 id

    /**
     * 扩展名
     */
    protected String type; // 扩展名

    /**
     * 大小, 默认Byte
     */
    protected Long size; // 大小

    /**
     * 文件名全称, uuid 名称, 需调用方法生成
     */
    protected String fileUUID;//新文件全称

    /**
     * getFilename 优先使用 originalFilename
     */
    protected boolean useOriginalFilenameFirst = true;


    @Override
    public boolean useOriginalFilenameFirst() {
        return useOriginalFilenameFirst;
    }


}
