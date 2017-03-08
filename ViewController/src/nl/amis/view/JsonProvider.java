package nl.amis.view;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.server.ViewRowSetImpl;

public class JsonProvider implements Map {
    private static ADFLogger _logger = ADFLogger.createADFLogger(JsonProvider.class);

    @Override
    public int size() {
        // TODO Implement this method
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Implement this method
        return false;
    }

    /***
     *   This method returns a String that contains a JSON formatted data object.
     *   This nested JSON object represents the data retrieved from the DCIteratorBinding passed into this method.
     *   The nested JSON object contains data for all rows and all attributes per row from the iterator directly as well as from nested rows.
     * 
     *   this method is supposed to be invoked with an DCIteratorBinding -- 
     *   especially a tree binding works well, defined in the PageDefinition as:
     *       <tree IterBinding="DeptView2Iterator" id="DeptView2">…
     *   The EL expression used in the page to send the iterator binding to this method is something like:
     *             var deptData = #{jsonProviderBean[bindings.DeptView2.DCIteratorBinding]};
     *             
     **/ 
    @Override
    public Object get(Object dataBinding) {
        String json = "{";
        try {
            DCIteratorBinding iterBind = (DCIteratorBinding) dataBinding;

            // write meta data in JSON object for all attributes
            AttributeDef[] attributeDefs = iterBind.getAttributeDefs();
            json = json + "\"attributes\": {";
            boolean firstAtt = true;
            for (AttributeDef attributeDef : attributeDefs) {
                json =
                    json + (firstAtt ? "" : ",") + "\"" + attributeDef.getName() + "\": \"" +
                    attributeDef.getJavaType().getCanonicalName() + "\"";
                firstAtt = false;
            }
            json = json + "}";
            // fetch values for all rows
            Row[] rows = iterBind.getAllRowsInRange();
            json = processRows(json + ", \"values\":", rows);
        } catch (Exception e) {
            _logger.severe("Exception" + e.getMessage(), e);
            json = json + ",\"exception\": \"" + e.getMessage() + "\"";
        }
        return json + "}";
    }

    private String processRows(String json, Row[] rows) {
        boolean firstAtt = true;
        json = json + "[";
        boolean firstRow = true;
        for (Row row : rows) {
            firstAtt = true;
            json = json + (firstRow ? "" : ",") + "{";
            firstRow = false;
            for (String attributeName : row.getAttributeNames()) {
                if (row.getAttribute(attributeName) instanceof ViewRowSetImpl) {
                    ViewRowSetImpl vrs = (ViewRowSetImpl) row.getAttribute(attributeName);
                    try {
                        json =
                            processRows(json + (firstAtt ? "\"" : ",\"") + attributeName + "\":", vrs.getAllRowsInRange());
                    } catch (Exception e) {
                        _logger.severe("Exception in nested rows " + e.getMessage(), e);
                    }
                } else {
                    json =
                        json + (firstAtt ? "" : ",") + "\"" + attributeName + "\": " + writeAttribute(row.getAttribute(attributeName));
                }
                //                 json = json +  ",\""+attributeName+"Type\": \""+ row.getAttribute(attributeName).getClass().getCanonicalName()+"\"";
                firstAtt = false;
            }
            json = json + "}";
        }
        json = json + "]";
        return json;
    }

    private String writeAttribute(Object attribute) {
        // if attribute is numeric, do not write quotation marks
        if (attribute == null) {
            return "null";
        }
        else
        if (attribute instanceof Integer || attribute instanceof BigDecimal|| attribute instanceof Number) {
            return  attribute.toString();            
        } else {            
            return  "\""+attribute+"\"";
        }
    }

    @Override
    public Object put(Object key, Object value) {
        // TODO Implement this method
        return null;
    }

    @Override
    public Object remove(Object key) {
        // TODO Implement this method
        return null;
    }

    @Override
    public void putAll(Map m) {
        // TODO Implement this method
    }

    @Override
    public void clear() {
        // TODO Implement this method
    }

    @Override
    public Set keySet() {
        // TODO Implement this method
        return Collections.emptySet();
    }

    @Override
    public Collection values() {
        // TODO Implement this method
        return Collections.emptySet();
    }

    @Override
    public Set entrySet() {
        // TODO Implement this method
        return Collections.emptySet();
    }

    public JsonProvider() {
        super();
    }
}

