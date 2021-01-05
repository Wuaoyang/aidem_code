// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: his_rri_data.proto
package com.aidem.cn.dto;
public final class HisRriData {
  private HisRriData() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface HisDataRRIOrBuilder extends
      // @@protoc_insertion_point(interface_extends:HisDataRRI)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required .DateTime time_stamp = 1;</code>
     */
    boolean hasTimeStamp();
    /**
     * <code>required .DateTime time_stamp = 1;</code>
     */
    RealtimeData.DateTime getTimeStamp();
    /**
     * <code>required .DateTime time_stamp = 1;</code>
     */
    RealtimeData.DateTimeOrBuilder getTimeStampOrBuilder();

    /**
     * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
     */
    java.util.List<Integer> getRawDataList();
    /**
     * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
     */
    int getRawDataCount();
    /**
     * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
     */
    int getRawData(int index);
  }
  /**
   * Protobuf type {@code HisDataRRI}
   */
  public  static final class HisDataRRI extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:HisDataRRI)
      HisDataRRIOrBuilder {
    // Use HisDataRRI.newBuilder() to construct.
    private HisDataRRI(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private HisDataRRI() {
      rawData_ = java.util.Collections.emptyList();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private HisDataRRI(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
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
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              RealtimeData.DateTime.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = timeStamp_.toBuilder();
              }
              timeStamp_ = input.readMessage(RealtimeData.DateTime.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(timeStamp_);
                timeStamp_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
              break;
            }
            case 21: {
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                rawData_ = new java.util.ArrayList<Integer>();
                mutable_bitField0_ |= 0x00000002;
              }
              rawData_.add(input.readFixed32());
              break;
            }
            case 18: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002) && input.getBytesUntilLimit() > 0) {
                rawData_ = new java.util.ArrayList<Integer>();
                mutable_bitField0_ |= 0x00000002;
              }
              while (input.getBytesUntilLimit() > 0) {
                rawData_.add(input.readFixed32());
              }
              input.popLimit(limit);
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
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          rawData_ = java.util.Collections.unmodifiableList(rawData_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return HisRriData.internal_static_HisDataRRI_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return HisRriData.internal_static_HisDataRRI_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              HisDataRRI.class, Builder.class);
    }

    private int bitField0_;
    public static final int TIME_STAMP_FIELD_NUMBER = 1;
    private RealtimeData.DateTime timeStamp_;
    /**
     * <code>required .DateTime time_stamp = 1;</code>
     */
    public boolean hasTimeStamp() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .DateTime time_stamp = 1;</code>
     */
    public RealtimeData.DateTime getTimeStamp() {
      return timeStamp_ == null ? RealtimeData.DateTime.getDefaultInstance() : timeStamp_;
    }
    /**
     * <code>required .DateTime time_stamp = 1;</code>
     */
    public RealtimeData.DateTimeOrBuilder getTimeStampOrBuilder() {
      return timeStamp_ == null ? RealtimeData.DateTime.getDefaultInstance() : timeStamp_;
    }

    public static final int RAW_DATA_FIELD_NUMBER = 2;
    private java.util.List<Integer> rawData_;
    /**
     * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
     */
    public java.util.List<Integer>
        getRawDataList() {
      return rawData_;
    }
    /**
     * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
     */
    public int getRawDataCount() {
      return rawData_.size();
    }
    /**
     * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
     */
    public int getRawData(int index) {
      return rawData_.get(index);
    }
    private int rawDataMemoizedSerializedSize = -1;

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasTimeStamp()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!getTimeStamp().isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeMessage(1, getTimeStamp());
      }
      if (getRawDataList().size() > 0) {
        output.writeUInt32NoTag(18);
        output.writeUInt32NoTag(rawDataMemoizedSerializedSize);
      }
      for (int i = 0; i < rawData_.size(); i++) {
        output.writeFixed32NoTag(rawData_.get(i));
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getTimeStamp());
      }
      {
        int dataSize = 0;
        dataSize = 4 * getRawDataList().size();
        size += dataSize;
        if (!getRawDataList().isEmpty()) {
          size += 1;
          size += com.google.protobuf.CodedOutputStream
              .computeInt32SizeNoTag(dataSize);
        }
        rawDataMemoizedSerializedSize = dataSize;
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof HisDataRRI)) {
        return super.equals(obj);
      }
      HisDataRRI other = (HisDataRRI) obj;

      boolean result = true;
      result = result && (hasTimeStamp() == other.hasTimeStamp());
      if (hasTimeStamp()) {
        result = result && getTimeStamp()
            .equals(other.getTimeStamp());
      }
      result = result && getRawDataList()
          .equals(other.getRawDataList());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      if (hasTimeStamp()) {
        hash = (37 * hash) + TIME_STAMP_FIELD_NUMBER;
        hash = (53 * hash) + getTimeStamp().hashCode();
      }
      if (getRawDataCount() > 0) {
        hash = (37 * hash) + RAW_DATA_FIELD_NUMBER;
        hash = (53 * hash) + getRawDataList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static HisDataRRI parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static HisDataRRI parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static HisDataRRI parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static HisDataRRI parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static HisDataRRI parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static HisDataRRI parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static HisDataRRI parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static HisDataRRI parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static HisDataRRI parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static HisDataRRI parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(HisDataRRI prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code HisDataRRI}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:HisDataRRI)
        HisDataRRIOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return HisRriData.internal_static_HisDataRRI_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return HisRriData.internal_static_HisDataRRI_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                HisDataRRI.class, Builder.class);
      }

      // Construct using HisRriData.HisDataRRI.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
          getTimeStampFieldBuilder();
        }
      }
      public Builder clear() {
        super.clear();
        if (timeStampBuilder_ == null) {
          timeStamp_ = null;
        } else {
          timeStampBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        rawData_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return HisRriData.internal_static_HisDataRRI_descriptor;
      }

      public HisDataRRI getDefaultInstanceForType() {
        return HisDataRRI.getDefaultInstance();
      }

      public HisDataRRI build() {
        HisDataRRI result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public HisDataRRI buildPartial() {
        HisDataRRI result = new HisDataRRI(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (timeStampBuilder_ == null) {
          result.timeStamp_ = timeStamp_;
        } else {
          result.timeStamp_ = timeStampBuilder_.build();
        }
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          rawData_ = java.util.Collections.unmodifiableList(rawData_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.rawData_ = rawData_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof HisDataRRI) {
          return mergeFrom((HisDataRRI)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(HisDataRRI other) {
        if (other == HisDataRRI.getDefaultInstance()) return this;
        if (other.hasTimeStamp()) {
          mergeTimeStamp(other.getTimeStamp());
        }
        if (!other.rawData_.isEmpty()) {
          if (rawData_.isEmpty()) {
            rawData_ = other.rawData_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureRawDataIsMutable();
            rawData_.addAll(other.rawData_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasTimeStamp()) {
          return false;
        }
        if (!getTimeStamp().isInitialized()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        HisDataRRI parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (HisDataRRI) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private RealtimeData.DateTime timeStamp_ = null;
      private com.google.protobuf.SingleFieldBuilderV3<
          RealtimeData.DateTime, RealtimeData.DateTime.Builder, RealtimeData.DateTimeOrBuilder> timeStampBuilder_;
      /**
       * <code>required .DateTime time_stamp = 1;</code>
       */
      public boolean hasTimeStamp() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .DateTime time_stamp = 1;</code>
       */
      public RealtimeData.DateTime getTimeStamp() {
        if (timeStampBuilder_ == null) {
          return timeStamp_ == null ? RealtimeData.DateTime.getDefaultInstance() : timeStamp_;
        } else {
          return timeStampBuilder_.getMessage();
        }
      }
      /**
       * <code>required .DateTime time_stamp = 1;</code>
       */
      public Builder setTimeStamp(RealtimeData.DateTime value) {
        if (timeStampBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          timeStamp_ = value;
          onChanged();
        } else {
          timeStampBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .DateTime time_stamp = 1;</code>
       */
      public Builder setTimeStamp(
          RealtimeData.DateTime.Builder builderForValue) {
        if (timeStampBuilder_ == null) {
          timeStamp_ = builderForValue.build();
          onChanged();
        } else {
          timeStampBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .DateTime time_stamp = 1;</code>
       */
      public Builder mergeTimeStamp(RealtimeData.DateTime value) {
        if (timeStampBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              timeStamp_ != null &&
              timeStamp_ != RealtimeData.DateTime.getDefaultInstance()) {
            timeStamp_ =
              RealtimeData.DateTime.newBuilder(timeStamp_).mergeFrom(value).buildPartial();
          } else {
            timeStamp_ = value;
          }
          onChanged();
        } else {
          timeStampBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .DateTime time_stamp = 1;</code>
       */
      public Builder clearTimeStamp() {
        if (timeStampBuilder_ == null) {
          timeStamp_ = null;
          onChanged();
        } else {
          timeStampBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>required .DateTime time_stamp = 1;</code>
       */
      public RealtimeData.DateTime.Builder getTimeStampBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getTimeStampFieldBuilder().getBuilder();
      }
      /**
       * <code>required .DateTime time_stamp = 1;</code>
       */
      public RealtimeData.DateTimeOrBuilder getTimeStampOrBuilder() {
        if (timeStampBuilder_ != null) {
          return timeStampBuilder_.getMessageOrBuilder();
        } else {
          return timeStamp_ == null ?
              RealtimeData.DateTime.getDefaultInstance() : timeStamp_;
        }
      }
      /**
       * <code>required .DateTime time_stamp = 1;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          RealtimeData.DateTime, RealtimeData.DateTime.Builder, RealtimeData.DateTimeOrBuilder>
          getTimeStampFieldBuilder() {
        if (timeStampBuilder_ == null) {
          timeStampBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              RealtimeData.DateTime, RealtimeData.DateTime.Builder, RealtimeData.DateTimeOrBuilder>(
                  getTimeStamp(),
                  getParentForChildren(),
                  isClean());
          timeStamp_ = null;
        }
        return timeStampBuilder_;
      }

      private java.util.List<Integer> rawData_ = java.util.Collections.emptyList();
      private void ensureRawDataIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          rawData_ = new java.util.ArrayList<Integer>(rawData_);
          bitField0_ |= 0x00000002;
         }
      }
      /**
       * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
       */
      public java.util.List<Integer>
          getRawDataList() {
        return java.util.Collections.unmodifiableList(rawData_);
      }
      /**
       * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
       */
      public int getRawDataCount() {
        return rawData_.size();
      }
      /**
       * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
       */
      public int getRawData(int index) {
        return rawData_.get(index);
      }
      /**
       * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
       */
      public Builder setRawData(
          int index, int value) {
        ensureRawDataIsMutable();
        rawData_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
       */
      public Builder addRawData(int value) {
        ensureRawDataIsMutable();
        rawData_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
       */
      public Builder addAllRawData(
          Iterable<? extends Integer> values) {
        ensureRawDataIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, rawData_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated fixed32 raw_data = 2 [packed = true];</code>
       */
      public Builder clearRawData() {
        rawData_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:HisDataRRI)
    }

    // @@protoc_insertion_point(class_scope:HisDataRRI)
    private static final HisDataRRI DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new HisDataRRI();
    }

    public static HisDataRRI getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @Deprecated public static final com.google.protobuf.Parser<HisDataRRI>
        PARSER = new com.google.protobuf.AbstractParser<HisDataRRI>() {
      public HisDataRRI parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new HisDataRRI(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<HisDataRRI> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<HisDataRRI> getParserForType() {
      return PARSER;
    }

    public HisDataRRI getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_HisDataRRI_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_HisDataRRI_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022his_rri_data.proto\032\023realtime_data.prot" +
      "o\"A\n\nHisDataRRI\022\035\n\ntime_stamp\030\001 \002(\0132\t.Da" +
      "teTime\022\024\n\010raw_data\030\002 \003(\007B\002\020\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          RealtimeData.getDescriptor(),
        }, assigner);
    internal_static_HisDataRRI_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_HisDataRRI_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_HisDataRRI_descriptor,
        new String[] { "TimeStamp", "RawData", });
    RealtimeData.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
