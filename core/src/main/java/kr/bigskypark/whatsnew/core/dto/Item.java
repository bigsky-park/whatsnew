package kr.bigskypark.whatsnew.core.dto;

import com.fasterxml.jackson.databind.JsonNode;
import kr.bigskypark.whatsnew.core.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Item {

  private String username;

  private ZonedDateTime runAt;

  private Category category;

  private List<JsonNode> data;
}
