package org.wallet.Models;

import lombok.*;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.CustomType;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;
import org.wallet.Models.Types.TransactionType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Model(table = "categories")
public class Category {
    @Id
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "category_name")
    @NonNull
    private String categoryName;
    @Column(name = "transaction_type")
    @CustomType(type_class = "TransactionType")
    @NonNull
    private TransactionType transactionType;
}
