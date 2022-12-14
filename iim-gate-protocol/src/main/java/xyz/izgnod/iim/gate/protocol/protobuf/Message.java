// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package xyz.izgnod.iim.gate.protocol.protobuf;

public final class Message {
  private Message() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:protocol.protobuf.Response)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *返回code
     * </pre>
     *
     * <code>int32 code = 1;</code>
     * @return The code.
     */
    int getCode();

    /**
     * <pre>
     *返回message
     * </pre>
     *
     * <code>string msg = 2;</code>
     * @return The msg.
     */
    java.lang.String getMsg();
    /**
     * <pre>
     *返回message
     * </pre>
     *
     * <code>string msg = 2;</code>
     * @return The bytes for msg.
     */
    com.google.protobuf.ByteString
        getMsgBytes();

    /**
     * <code>.google.protobuf.Any data = 3;</code>
     * @return Whether the data field is set.
     */
    boolean hasData();
    /**
     * <code>.google.protobuf.Any data = 3;</code>
     * @return The data.
     */
    com.google.protobuf.Any getData();
    /**
     * <code>.google.protobuf.Any data = 3;</code>
     */
    com.google.protobuf.AnyOrBuilder getDataOrBuilder();
  }
  /**
   * Protobuf type {@code protocol.protobuf.Response}
   */
  public static final class Response extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:protocol.protobuf.Response)
      ResponseOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Response.newBuilder() to construct.
    private Response(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Response() {
      msg_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Response();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Response(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              code_ = input.readInt32();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              msg_ = s;
              break;
            }
            case 26: {
              com.google.protobuf.Any.Builder subBuilder = null;
              if (data_ != null) {
                subBuilder = data_.toBuilder();
              }
              data_ = input.readMessage(com.google.protobuf.Any.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(data_);
                data_ = subBuilder.buildPartial();
              }

              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return xyz.izgnod.iim.gate.protocol.protobuf.Message.internal_static_protocol_protobuf_Response_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return xyz.izgnod.iim.gate.protocol.protobuf.Message.internal_static_protocol_protobuf_Response_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              xyz.izgnod.iim.gate.protocol.protobuf.Message.Response.class, xyz.izgnod.iim.gate.protocol.protobuf.Message.Response.Builder.class);
    }

    public static final int CODE_FIELD_NUMBER = 1;
    private int code_;
    /**
     * <pre>
     *返回code
     * </pre>
     *
     * <code>int32 code = 1;</code>
     * @return The code.
     */
    @java.lang.Override
    public int getCode() {
      return code_;
    }

    public static final int MSG_FIELD_NUMBER = 2;
    private volatile java.lang.Object msg_;
    /**
     * <pre>
     *返回message
     * </pre>
     *
     * <code>string msg = 2;</code>
     * @return The msg.
     */
    @java.lang.Override
    public java.lang.String getMsg() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        msg_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *返回message
     * </pre>
     *
     * <code>string msg = 2;</code>
     * @return The bytes for msg.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getMsgBytes() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        msg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int DATA_FIELD_NUMBER = 3;
    private com.google.protobuf.Any data_;
    /**
     * <code>.google.protobuf.Any data = 3;</code>
     * @return Whether the data field is set.
     */
    @java.lang.Override
    public boolean hasData() {
      return data_ != null;
    }
    /**
     * <code>.google.protobuf.Any data = 3;</code>
     * @return The data.
     */
    @java.lang.Override
    public com.google.protobuf.Any getData() {
      return data_ == null ? com.google.protobuf.Any.getDefaultInstance() : data_;
    }
    /**
     * <code>.google.protobuf.Any data = 3;</code>
     */
    @java.lang.Override
    public com.google.protobuf.AnyOrBuilder getDataOrBuilder() {
      return getData();
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (code_ != 0) {
        output.writeInt32(1, code_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(msg_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, msg_);
      }
      if (data_ != null) {
        output.writeMessage(3, getData());
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (code_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, code_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(msg_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, msg_);
      }
      if (data_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, getData());
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof xyz.izgnod.iim.gate.protocol.protobuf.Message.Response)) {
        return super.equals(obj);
      }
      xyz.izgnod.iim.gate.protocol.protobuf.Message.Response other = (xyz.izgnod.iim.gate.protocol.protobuf.Message.Response) obj;

      if (getCode()
          != other.getCode()) return false;
      if (!getMsg()
          .equals(other.getMsg())) return false;
      if (hasData() != other.hasData()) return false;
      if (hasData()) {
        if (!getData()
            .equals(other.getData())) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + CODE_FIELD_NUMBER;
      hash = (53 * hash) + getCode();
      hash = (37 * hash) + MSG_FIELD_NUMBER;
      hash = (53 * hash) + getMsg().hashCode();
      if (hasData()) {
        hash = (37 * hash) + DATA_FIELD_NUMBER;
        hash = (53 * hash) + getData().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(xyz.izgnod.iim.gate.protocol.protobuf.Message.Response prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code protocol.protobuf.Response}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:protocol.protobuf.Response)
        xyz.izgnod.iim.gate.protocol.protobuf.Message.ResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return xyz.izgnod.iim.gate.protocol.protobuf.Message.internal_static_protocol_protobuf_Response_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return xyz.izgnod.iim.gate.protocol.protobuf.Message.internal_static_protocol_protobuf_Response_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                xyz.izgnod.iim.gate.protocol.protobuf.Message.Response.class, xyz.izgnod.iim.gate.protocol.protobuf.Message.Response.Builder.class);
      }

      // Construct using xyz.izgnod.iim.gate.protocol.protobuf.Message.Response.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        code_ = 0;

        msg_ = "";

        if (dataBuilder_ == null) {
          data_ = null;
        } else {
          data_ = null;
          dataBuilder_ = null;
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return xyz.izgnod.iim.gate.protocol.protobuf.Message.internal_static_protocol_protobuf_Response_descriptor;
      }

      @java.lang.Override
      public xyz.izgnod.iim.gate.protocol.protobuf.Message.Response getDefaultInstanceForType() {
        return xyz.izgnod.iim.gate.protocol.protobuf.Message.Response.getDefaultInstance();
      }

      @java.lang.Override
      public xyz.izgnod.iim.gate.protocol.protobuf.Message.Response build() {
        xyz.izgnod.iim.gate.protocol.protobuf.Message.Response result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public xyz.izgnod.iim.gate.protocol.protobuf.Message.Response buildPartial() {
        xyz.izgnod.iim.gate.protocol.protobuf.Message.Response result = new xyz.izgnod.iim.gate.protocol.protobuf.Message.Response(this);
        result.code_ = code_;
        result.msg_ = msg_;
        if (dataBuilder_ == null) {
          result.data_ = data_;
        } else {
          result.data_ = dataBuilder_.build();
        }
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof xyz.izgnod.iim.gate.protocol.protobuf.Message.Response) {
          return mergeFrom((xyz.izgnod.iim.gate.protocol.protobuf.Message.Response)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(xyz.izgnod.iim.gate.protocol.protobuf.Message.Response other) {
        if (other == xyz.izgnod.iim.gate.protocol.protobuf.Message.Response.getDefaultInstance()) return this;
        if (other.getCode() != 0) {
          setCode(other.getCode());
        }
        if (!other.getMsg().isEmpty()) {
          msg_ = other.msg_;
          onChanged();
        }
        if (other.hasData()) {
          mergeData(other.getData());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        xyz.izgnod.iim.gate.protocol.protobuf.Message.Response parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (xyz.izgnod.iim.gate.protocol.protobuf.Message.Response) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int code_ ;
      /**
       * <pre>
       *返回code
       * </pre>
       *
       * <code>int32 code = 1;</code>
       * @return The code.
       */
      @java.lang.Override
      public int getCode() {
        return code_;
      }
      /**
       * <pre>
       *返回code
       * </pre>
       *
       * <code>int32 code = 1;</code>
       * @param value The code to set.
       * @return This builder for chaining.
       */
      public Builder setCode(int value) {
        
        code_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *返回code
       * </pre>
       *
       * <code>int32 code = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearCode() {
        
        code_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object msg_ = "";
      /**
       * <pre>
       *返回message
       * </pre>
       *
       * <code>string msg = 2;</code>
       * @return The msg.
       */
      public java.lang.String getMsg() {
        java.lang.Object ref = msg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          msg_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *返回message
       * </pre>
       *
       * <code>string msg = 2;</code>
       * @return The bytes for msg.
       */
      public com.google.protobuf.ByteString
          getMsgBytes() {
        java.lang.Object ref = msg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          msg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *返回message
       * </pre>
       *
       * <code>string msg = 2;</code>
       * @param value The msg to set.
       * @return This builder for chaining.
       */
      public Builder setMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        msg_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *返回message
       * </pre>
       *
       * <code>string msg = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearMsg() {
        
        msg_ = getDefaultInstance().getMsg();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *返回message
       * </pre>
       *
       * <code>string msg = 2;</code>
       * @param value The bytes for msg to set.
       * @return This builder for chaining.
       */
      public Builder setMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        msg_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.Any data_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> dataBuilder_;
      /**
       * <code>.google.protobuf.Any data = 3;</code>
       * @return Whether the data field is set.
       */
      public boolean hasData() {
        return dataBuilder_ != null || data_ != null;
      }
      /**
       * <code>.google.protobuf.Any data = 3;</code>
       * @return The data.
       */
      public com.google.protobuf.Any getData() {
        if (dataBuilder_ == null) {
          return data_ == null ? com.google.protobuf.Any.getDefaultInstance() : data_;
        } else {
          return dataBuilder_.getMessage();
        }
      }
      /**
       * <code>.google.protobuf.Any data = 3;</code>
       */
      public Builder setData(com.google.protobuf.Any value) {
        if (dataBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          data_ = value;
          onChanged();
        } else {
          dataBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any data = 3;</code>
       */
      public Builder setData(
          com.google.protobuf.Any.Builder builderForValue) {
        if (dataBuilder_ == null) {
          data_ = builderForValue.build();
          onChanged();
        } else {
          dataBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any data = 3;</code>
       */
      public Builder mergeData(com.google.protobuf.Any value) {
        if (dataBuilder_ == null) {
          if (data_ != null) {
            data_ =
              com.google.protobuf.Any.newBuilder(data_).mergeFrom(value).buildPartial();
          } else {
            data_ = value;
          }
          onChanged();
        } else {
          dataBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any data = 3;</code>
       */
      public Builder clearData() {
        if (dataBuilder_ == null) {
          data_ = null;
          onChanged();
        } else {
          data_ = null;
          dataBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any data = 3;</code>
       */
      public com.google.protobuf.Any.Builder getDataBuilder() {
        
        onChanged();
        return getDataFieldBuilder().getBuilder();
      }
      /**
       * <code>.google.protobuf.Any data = 3;</code>
       */
      public com.google.protobuf.AnyOrBuilder getDataOrBuilder() {
        if (dataBuilder_ != null) {
          return dataBuilder_.getMessageOrBuilder();
        } else {
          return data_ == null ?
              com.google.protobuf.Any.getDefaultInstance() : data_;
        }
      }
      /**
       * <code>.google.protobuf.Any data = 3;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> 
          getDataFieldBuilder() {
        if (dataBuilder_ == null) {
          dataBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder>(
                  getData(),
                  getParentForChildren(),
                  isClean());
          data_ = null;
        }
        return dataBuilder_;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:protocol.protobuf.Response)
    }

    // @@protoc_insertion_point(class_scope:protocol.protobuf.Response)
    private static final xyz.izgnod.iim.gate.protocol.protobuf.Message.Response DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new xyz.izgnod.iim.gate.protocol.protobuf.Message.Response();
    }

    public static xyz.izgnod.iim.gate.protocol.protobuf.Message.Response getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Response>
        PARSER = new com.google.protobuf.AbstractParser<Response>() {
      @java.lang.Override
      public Response parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Response(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Response> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Response> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public xyz.izgnod.iim.gate.protocol.protobuf.Message.Response getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protocol_protobuf_Response_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protocol_protobuf_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rmessage.proto\022\021protocol.protobuf\032\031goog" +
      "le/protobuf/any.proto\"I\n\010Response\022\014\n\004cod" +
      "e\030\001 \001(\005\022\013\n\003msg\030\002 \001(\t\022\"\n\004data\030\003 \001(\0132\024.goo" +
      "gle.protobuf.AnyB0\n%xyz.izgnod.iim.gate." +
      "protocol.protobufB\007Messageb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.AnyProto.getDescriptor(),
        });
    internal_static_protocol_protobuf_Response_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_protocol_protobuf_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protocol_protobuf_Response_descriptor,
        new java.lang.String[] { "Code", "Msg", "Data", });
    com.google.protobuf.AnyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
