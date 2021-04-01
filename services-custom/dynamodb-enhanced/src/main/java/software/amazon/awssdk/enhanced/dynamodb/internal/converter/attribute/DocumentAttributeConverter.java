/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute;

import software.amazon.awssdk.annotations.SdkInternalApi;
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

/**
 * {@link AttributeConverter} for converting nested table schemas
 */
@SdkInternalApi
public class DocumentAttributeConverter<T> implements AttributeConverter<T> {

    private final TableSchema<T> tableSchema;
    private final EnhancedType<T> enhancedType;
    private final boolean preserveEmptyBean;

    private DocumentAttributeConverter(Builder<T> builder) {
        this.tableSchema = builder.tableSchema;
        this.enhancedType = builder.enhancedType;
        this.preserveEmptyBean = builder.preserveEmptyBean != null && builder.preserveEmptyBean;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    @Override
    public AttributeValue transformFrom(T input) {
        return AttributeValue.builder().m(tableSchema.itemToMap(input, false)).build();
    }

    @Override
    public T transformTo(AttributeValue input) {
        return tableSchema.mapToItem(input.m(), preserveEmptyBean);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.M;
    }

    @Override
    public EnhancedType<T> type() {
        return enhancedType;
    }

    public static final class Builder<T> {
        private TableSchema<T> tableSchema;
        private EnhancedType<T> enhancedType;
        private Boolean preserveEmptyBean;

        private Builder() {
        }

        public Builder<T> tableSchema(TableSchema<T> tableSchema) {
            this.tableSchema = tableSchema;
            return this;
        }

        public Builder<T> enhancedType(EnhancedType<T> enhancedType) {
            this.enhancedType = enhancedType;
            return this;
        }

        public Builder<T> preserveEmptyBean(Boolean preserveEmptyBean) {
            this.preserveEmptyBean = preserveEmptyBean;
            return this;
        }

        public DocumentAttributeConverter<T> build() {
            return new DocumentAttributeConverter<>(this);
        }
    }
}
