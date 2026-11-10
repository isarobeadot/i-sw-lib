package inaki.sw.lib.dto;

import lombok.With;

/**
 * @author inaki
 * @since 1.1
 */
@With
public record IntegerDTO(Integer value) implements IDTO {
}
