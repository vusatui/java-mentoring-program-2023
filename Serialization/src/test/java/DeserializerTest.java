import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.vusatui.dto.UserDTO;
import com.vusatui.io.Deserializer;
import com.vusatui.io.Serializer;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
class DeserializerTest {

    private final Serializer serializer = new Serializer();

    private final Deserializer deserializer = new Deserializer();

    @Test
    void whenSerializingAndDeserializing_ThenObjectIsTheSame() throws IOException, ClassNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Anton");
        userDTO.setSurname("Vusatyi");

        byte[] bytes = serializer.serialize(userDTO);

        UserDTO deserializedUserDTO = deserializer.deserialize(bytes);

        assertEquals(deserializedUserDTO.getName(), userDTO.getName());
        assertEquals(deserializedUserDTO.getSurname(), userDTO.getSurname());
    }
}
