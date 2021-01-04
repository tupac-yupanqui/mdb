package de.wko.mdb.rest.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wko.mdb.data.filter.AlbumFilter;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class FilterEditor extends PropertyEditorSupport {
    ObjectMapper objectMapper;

    public FilterEditor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            System.out.println(text);
            AlbumFilter filter = new AlbumFilter();
            try {
                filter = objectMapper.readValue(text, AlbumFilter.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(filter);
        }
    }
}
