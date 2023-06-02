public class A {
    public static Map<String, MetadataFieldMapper.TypeParser> getMetadataMappers(List<MapperPlugin> mapperPlugins) {
        Map<String, MetadataFieldMapper.TypeParser> metadataMappers = new LinkedHashMap<>();
        int i = 0;
        Map.Entry<String, MetadataFieldMapper.TypeParser> fieldNamesEntry = null;
        for (Map.Entry<String, MetadataFieldMapper.TypeParser> entry : builtInMetadataMappers.entrySet()) {
            if (i < builtInMetadataMappers.size() - 1) {
                metadataMappers.put(entry.getKey(), entry.getValue());
            } else {
                assert entry.getKey().equals(FieldNamesFieldMapper.NAME) : "_field_names must be the last registered mapper, order counts";
                fieldNamesEntry = entry;
            }
            i++;
        }
        ...
                if (metadataMappers.put(entry.getKey(), entry.getValue()) != null) {
                    ...
                }
}
