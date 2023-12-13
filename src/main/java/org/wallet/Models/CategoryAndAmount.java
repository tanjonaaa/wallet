package org.wallet.Models;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CategoryAndAmount {
    private Map<String, Double> matching;
}
