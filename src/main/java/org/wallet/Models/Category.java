package org.wallet.Models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Category {
    private String categoryId;
    private String categoryName;
    private String transactionType;
}
