package dev.sol.libs.xml;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class JAXBLib {
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(File file, Class<T> t) {
        try {
            JAXBContext context = JAXBContext.newInstance(t);
            Unmarshaller um = context.createUnmarshaller();
            return (T) um.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> Boolean serialize(File file, T t) {
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Marshaller m = context.createMarshaller();
            m.marshal(t, file);
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }
}
