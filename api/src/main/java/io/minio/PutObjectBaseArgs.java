/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2020 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.minio;

import com.google.common.base.Objects;
import java.io.IOException;

/** Base argument class for {@link PutObjectArgs} and {@link UploadObjectArgs}. */
public abstract class PutObjectBaseArgs extends ObjectWriteArgs {
  protected long objectSize;
  protected long partSize;
  protected int partCount;
  protected String contentType;

  public long objectSize() {
    return objectSize;
  }

  public long partSize() {
    return partSize;
  }

  public int partCount() {
    return partCount;
  }

  /** Gets content type. It returns if content type is set (or) value of "Content-Type" header. */
  public String contentType() throws IOException {
    if (contentType != null) {
      return contentType;
    }

    if (this.headers().containsKey("Content-Type")) {
      return this.headers().get("Content-Type").iterator().next();
    }

    return null;
  }

  /** Base argument builder class for {@link PutObjectBaseArgs}. */
  public abstract static class Builder<B extends Builder<B, A>, A extends PutObjectBaseArgs>
      extends ObjectWriteArgs.Builder<B, A> {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PutObjectBaseArgs)) return false;
    if (!super.equals(o)) return false;
    PutObjectBaseArgs that = (PutObjectBaseArgs) o;
    return objectSize == that.objectSize
        && partSize == that.partSize
        && partCount == that.partCount
        && Objects.equal(contentType, that.contentType);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), objectSize, partSize, partCount, contentType);
  }
}
