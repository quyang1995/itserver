package com.longfor.itserver.common.helper;

import com.longfor.itserver.common.Datatable.Datatable;
import com.longfor.itserver.common.Datatable.Item;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author mayee
 * Created on 2017/2/10 下午4:33
 *
 * @version v1.0
 */
public class DatatablesHelper {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private String version;
    private Map<String, String> globalValueMap;
    private Map<String, Datatable> datatablesMap;
    private Map<String, Object> languageMap;

    private DatatablesHelper() {
        initDatatablesHelper();
    }

    private static final DatatablesHelper datatablesHelper = new DatatablesHelper();

    public static DatatablesHelper getInstance() {
        return datatablesHelper;
    }

    private void initDatatablesHelper() {
        loadDatatablesXml();
    }

    public String getGlobalValue(String name) {
        return globalValueMap.get(name);
    }

    public Map<String, Object> getLanguageMap() {
        return languageMap;
    }

    public Datatable getDatatable(String id) {
        return datatablesMap.get(id);
    }

    public String getVersion() {
        return version;
    }

    private void addItemElementForMap(Element e, Map map) {
        String name = "";
        String text = "";
        String defText = "";

        Attribute e_name = e.attribute("name");
        if (e_name != null) {
            name = e_name.getText();
        }
        Attribute e_text = e.attribute("text");
        if (e_text != null) {
            text = e_text.getText();
        }
        Attribute e_defText = e.attribute("defText");
        if (e_defText != null) {
            defText = e_defText.getText();
        }
        Item item = new Item(name, text, defText);
        map.put(name, item);
    }

    private void addGlobalValueForMap(Element e, Map map) {
        String name = "";
        String value = "";

        Attribute e_name = e.attribute("name");
        if (e_name != null) {
            name = e_name.getText();
        }
        Attribute e_value = e.attribute("value");
        if (e_value != null) {
            value = e_value.getText();
        }
        map.put(name, value);
    }

    private void loadDatatablesXml() {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(JoddHelper.getInstance().read("/xml/datatables.xml"));
        } catch (Exception e) {
            LOG.error("**** DatatablesHelper -> loadDatatablesXml fail! ****", e);
            return;
        }

        /* reset */
        datatablesMap = new HashMap<String, Datatable>();
        globalValueMap = new HashMap<String, String>();
        languageMap = new HashMap<String, Object>();

        Element root = document.getRootElement();
        for (Iterator iterA = root.elementIterator(); iterA.hasNext(); ) {
            // 获取节点 version/globalValue/datatables
            Element elementA = (Element) iterA.next();
            if (elementA != null && elementA.hasContent()) {
                if ("datatables".equals(elementA.getName())) {
                    // 获取节点 datatable
                    for (Iterator iterB = elementA.elementIterator(); iterB
                            .hasNext(); ) {
                        Element elementB = (Element) iterB.next();
                        if (elementB != null && "datatable".equals(elementB.getName())) {
                            Attribute attrB_id = elementB.attribute("id");
                            Attribute attrB_name = elementB.attribute("name");

                            //create datatable
                            Datatable datatable = new Datatable(
                                    attrB_id.getText(), attrB_name.getText()
                            );

                            if (elementB.hasContent()) {
                                // 获取节点 property
                                for (Iterator iterC = elementB.elementIterator(); iterC
                                        .hasNext(); ) {
                                    Element elementC = (Element) iterC.next();
                                    if (elementC != null && "property".equals(elementC.getName())) {

                                        Attribute attrC_name = elementC.attribute("name");
                                        Attribute attrC_value = elementC.attribute("value");
                                        Attribute attrC_url = elementC.attribute("isURL");

                                        if (attrC_url != null && "true".equals(attrC_url.getText())) {
                                            datatable.setProperty(
                                                    attrC_name.getText(),
                                                            attrC_value.getText()
                                            );
                                        } else {
                                            datatable.setProperty(
                                                    attrC_name.getText(),
                                                    attrC_value.getText()
                                            );
                                        }


                                    }
                                }//property end
                            }
                            datatablesMap.put(datatable.getId(), datatable);
                        }
                    }// datatable end
                }// datatables end
                else if ("language".equals(elementA.getName())) {
                    // 获取节点 item
                    for (Iterator iterB = elementA.elementIterator(); iterB
                            .hasNext(); ) {
                        Element elementB = (Element) iterB.next();
                        if (elementB != null) {
                            if ("item".equals(elementB.getName())) {
                                addItemElementForMap(elementB, languageMap);
                            } else if ("paginate".equals(elementB.getName())) {
                                Map<String, Item> paginateMap = new HashMap<String, Item>();
                                for (Iterator iterC = elementB.elementIterator(); iterC
                                        .hasNext(); ) {
                                    Element elementC = (Element) iterC.next();
                                    if (elementC != null && "item".equals(elementC.getName())) {
                                        addItemElementForMap(elementC, paginateMap);
                                    }
                                }
                                languageMap.put("paginateHM", paginateMap);
                            }
                        }

                    }// item end
                }
            } else {
                if (elementA != null) {
                    if ("version".equals(elementA.getName())) {
                        this.version = elementA.attribute("value").getText();
                    }
                    else if ("globalValue".equals(elementA.getName())) {
                        addGlobalValueForMap(elementA, globalValueMap);
                    }
                }

            }
        }
    }
}
