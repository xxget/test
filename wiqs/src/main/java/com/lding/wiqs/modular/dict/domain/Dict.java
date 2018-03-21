package com.lding.wiqs.modular.dict.domain;

import javax.persistence.*;

@Table(name = "c_dict")
public class Dict {
    @Id
    @Column(name = "dict_id")
    private String dictId;

    /**
     * 字典类型
     */
    @Column(name = "dict_type")
    private String dictType;

    /**
     * Key
     */
    @Column(name = "dict_dkey")
    private String dictDkey;

    /**
     * 字典值
     */
    @Column(name = "dict_value")
    private String dictValue;

    /**
     * 说明
     */
    private String text;

    /**
     * @return dict_id
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * @param dictId
     */
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    /**
     * 获取字典类型
     *
     * @return dict_type - 字典类型
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * 设置字典类型
     *
     * @param dictType 字典类型
     */
    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    /**
     * 获取Key
     *
     * @return dict_dkey - Key
     */
    public String getDictDkey() {
        return dictDkey;
    }

    /**
     * 设置Key
     *
     * @param dictDkey Key
     */
    public void setDictDkey(String dictDkey) {
        this.dictDkey = dictDkey;
    }

    /**
     * 获取字典值
     *
     * @return dict_value - 字典值
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * 设置字典值
     *
     * @param dictValue 字典值
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    /**
     * 获取说明
     *
     * @return text - 说明
     */
    public String getText() {
        return text;
    }

    /**
     * 设置说明
     *
     * @param text 说明
     */
    public void setText(String text) {
        this.text = text;
    }
}