package org.wallet.Models;

import lombok.*;

import java.util.Map;
import java.util.Objects;


@Builder
public class CategoryAndAmount {
    private Map<String, Double> matching;

    public CategoryAndAmount(Map<String, Double> matching) {
        this.matching = matching;
    }

    public CategoryAndAmount() {
    }

    public Map<String, Double> getMatching() {
        return matching;
    }

    public void setMatching(Map<String, Double> matching) {
        this.matching = matching;
    }

    @Override
    public String toString() {
        return "CategoryAndAmount{" +
                "matching=" + matching +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryAndAmount that = (CategoryAndAmount) o;
        return Objects.equals(matching, that.matching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matching);
    }
}
