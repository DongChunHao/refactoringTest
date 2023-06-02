public class A {
    public static Map<String, MetadataFieldMapper.TypeParser> getMetadataMappers(List<MapperPlugin> mapperPlugins) {
        Map<String, MetadataFieldMapper.TypeParser> metadataMappers = new LinkedHashMap<>(builtInMetadataMappers);

        Map.Entry<String, MetadataFieldMapper.TypeParser> fieldNamesEntry = metadataMappers.remove(FieldNamesFieldMapper.NAME);
        ...
                if (metadataMappers.putIfAbsent(entry.getKey(), entry.getValue()) != null) {
                    ...
                }
}
