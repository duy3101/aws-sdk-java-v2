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

package software.amazon.awssdk.enhanced.dynamodb;


import software.amazon.awssdk.annotations.SdkPublicApi;
import software.amazon.awssdk.utils.builder.CopyableBuilder;
import software.amazon.awssdk.utils.builder.ToCopyableBuilder;

/**
 * Configuration for {@link EnhancedType}
 */
@SdkPublicApi
public final class EnhancedTypeConfiguration implements ToCopyableBuilder<EnhancedTypeConfiguration.Builder,
    EnhancedTypeConfiguration> {
    private final boolean preserveEmptyBean;

    public EnhancedTypeConfiguration(Builder builder) {
        this.preserveEmptyBean = builder.preserveEmptyBean != null && builder.preserveEmptyBean;
    }

    /**
     * @return whether to initialize the associated {@link EnhancedType} as empty class when
     * mapping it to a Java object
     */
    public boolean preserveEmptyBean() {
        return preserveEmptyBean;
    }

    @Override
    public Builder toBuilder() {
        return builder().preserveEmptyBean(preserveEmptyBean);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder implements CopyableBuilder<Builder, EnhancedTypeConfiguration> {
        private Boolean preserveEmptyBean;

        private Builder() {
        }

        /**
         * Specifies whether to initialize the associated {@link EnhancedType} as empty class when
         * mapping it to a Java object
         */
        public Builder preserveEmptyBean(Boolean preserveEmtpyBean) {
            this.preserveEmptyBean = preserveEmtpyBean;
            return this;
        }

        @Override
        public EnhancedTypeConfiguration build() {
            return new EnhancedTypeConfiguration(this);
        }
    }
}
