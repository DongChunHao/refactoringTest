public class A {
    public static Map<String, MetadataFieldMapper.TypeParser> getMetadataMappers(List<MapperPlugin> mapperPlugins) {
        Map<String, MetadataFieldMapper.TypeParser> metadataMappers = new LinkedHashMap<>(builtInMetadataMappers);

        Map.Entry<String, MetadataFieldMapper.TypeParser> fieldNamesEntry = metadataMappers.remove(FieldNamesFieldMapper.NAME);
        assert fieldNamesEntry != null;

        for (MapperPlugin mapperPlugin : mapperPlugins) {
            for (Map.Entry<String, MetadataFieldMapper.TypeParser> entry : mapperPlugin.getMetadataMappers().entrySet()) {
                if (entry.getKey().equals(FieldNamesFieldMapper.NAME)) {
                    throw new IllegalArgumentException("Plugin cannot contain metadata mapper [" + FieldNamesFieldMapper.NAME + "]");
                }
                if (metadataMappers.putIfAbsent(entry.getKey(), entry.getValue()) != null) {
                    throw new IllegalArgumentException("MetadataFieldMapper [" + entry.getKey() + "] is already registered");
                }
            }
        }

        metadataMappers.put(fieldNamesEntry.getKey(), fieldNamesEntry.getValue());
        return Collections.unmodifiableMap(metadataMappers);
    }

}
