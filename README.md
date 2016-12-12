### solr 拼接查询条件的封装方法


       利用注解和反射开发的一套工具类，用来生成solr的查询语句。
       
#####（1）根据业务需求的传参，动态映射入参与solr的fieldName字段的映射

        比如：sClientType -- xf_show_tags
             sRegionIDs  -- xf_region_relation
             
             
        配置关系：
        @SolrField(
            type = SolrSearchType.FQ, 
            requestName = "sClientType", 
            solrName = "xf_show_tags")    
            
            

#####（2）需要接口类，都要implements SolrBaseQuery , 这样的做法，程序就可以动态的加载class文件，将接口实现类与对应的配置类型相映射  (策略模式与工厂模式的结合，避免了大量的if-else)

    
         代码实现类：com.junjincode.solr.query.WrapSolrQueryByAnnotation



#####（3）打印结果

       fq=xf_desc:万科+OR+xf_kycTag:超便宜+OR+xf_tag:东方明珠
       &fq=xf_show_tags:list
       &fq=xf_price:[10+TO+20]
       &fq=xf_region_relation:1
       &q=1
       &defType=edismax
       &qf=xf_name^3+xf_region_relation^0.8+xf_address^0.6
       &group=true
       &group.field=xf_id
       &group.ngroups=true
       &group.limit=1
       &hl=true
       &hl.fl=xf_name,xf_city_name,xf_alia,xf_area_name,xf_plate_name,xf_feature,xf_address
                    
            
            
            