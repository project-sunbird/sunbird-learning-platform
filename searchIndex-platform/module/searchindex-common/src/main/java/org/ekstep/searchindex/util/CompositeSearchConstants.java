package org.ekstep.searchindex.util;

import org.ekstep.common.Platform;

public class CompositeSearchConstants {
	public static String COMPOSITE_SEARCH_INDEX = Platform.config.hasPath("compositesearch.index.name") ? Platform.config.getString("compositesearch.index.name"): "compositesearch";
	public static final String OPERATION_CREATE = "CREATE";
	public static final String OPERATION_UPDATE = "UPDATE";
	public static final String OPERATION_DELETE = "DELETE";
	public static final String OBJECT_TYPE_DOMAIN = "DOMAIN";
	public static final String OBJECT_TYPE_WORD = "WORD";
	public static final String OBJECT_TYPE_DIMENSION = "DIMENSION";
	public static final String OBJECT_TYPE_CONCEPT = "CONCEPT";
	public static final String OBJECT_TYPE_METHOD = "METHOD";
	public static final String OBJECT_TYPE_MISCONCEPTION = "MISCONCEPTION";
	public static final String OBJECT_TYPE_CONTENT = "CONTENT";
	public static final String OBJECT_TYPE_ASSESMENT_ITEM = "ASSESMENTITEM";
	public static final String OBJECT_TYPE_ITEM_SET = "ITEM_SET";
	public static final String NODE_TYPE_DATA = "DATA_NODE";
	public static final String NODE_TYPE_SET = "SET";
	public static final String NODE_TYPE_ROOT = "ROOT_NODE";
	public static final String COMPOSITE_SEARCH_INDEX_TYPE = "cs";
	public static final String INDEX_FIELD_TAGS = "tags";
	public static final String NODE_TYPE_DEFINITION = "DEFINITION_NODE";
	public static final String SEARCH_OPERATION_LESS_THAN = "<";
	public static final String SEARCH_OPERATION_GREATER_THAN = ">";
	public static final String SEARCH_OPERATION_EXISTS = "EXISTS";
	public static final String SEARCH_OPERATION_NOT_EXISTS = "NT_EXISTS";
	public static final String SEARCH_OPERATION_LESS_THAN_EQUALS = "<=";
	public static final String SEARCH_OPERATION_GREATER_THAN_EQUALS = ">=";
	public static final String SEARCH_OPERATION_LIKE = "LIKE";
	public static final String SEARCH_OPERATION_CONTAINS = "CONTAINS";
	public static final String SEARCH_OPERATION_NOT_LIKE = "NT_LIKE";
	public static final String SEARCH_OPERATION_EQUAL = "EQ";
	public static final String SEARCH_OPERATION_NOT_EQUAL = "NT_EQ";
	public static final String SEARCH_OPERATION_NOT_EQUAL_OPERATOR = "!=";
	public static final String SEARCH_OPERATION_NOT_EQUAL_TEXT = "notEquals";
	public static final String SEARCH_OPERATION_NOT_EQUAL_TEXT_UPPERCASE = "NE";
	public static final String SEARCH_OPERATION_NOT_EQUAL_TEXT_LOWERCASE = "ne";
	public static final String SEARCH_OPERATION_STARTS_WITH = "SW";
	public static final String SEARCH_OPERATION_ENDS_WITH = "EW";
	public static final String SEARCH_OPERATION_OR = "OR";
	public static final String SEARCH_OPERATION_AND = "AND";
	public static final String SEARCH_OPERATION_IN = "IN";
	public static final String CONSUMER_TYPE_COMPOSITE_INDEX = "composite_index";
	public static final String CONSUMER_TYPE_WORD_COUNT = "word_count";
	public static final String FACET_COUNT_KEY = "_count";
	public static final String RAW_FIELD_EXTENSION = ".raw";
	public static final String SEARCH_OPERATION_RANGE_MIN = "min";
	public static final String SEARCH_OPERATION_RANGE_MAX = "max";
	public static final String CONDITION_SET_RANGE = "Range";
	public static final String CONDITION_SET_ARITHMETIC="Arithmetic";
	public static final String CONDITION_SET_MUST= "Must";
	public static final String CONDITION_SET_MUST_NOT= "Not";
	public static final String SEARCH_OPERATION_RANGE = "range";
	public static final String SEARCH_OPERATION_RANGE_GTE="gte";
	public static final String SEARCH_OPERATION_RANGE_LTE="lte";
	public static final String CONDITION_SET_SHOULD = "should";
	public static final String SEARCH_OPERATION_SOFT = "soft";
	public static final String NODE_TYPE_EXTERNAL = "EXTERNAL";
	public static final String NODE_TYPE_DIALCODE_METRICS = "DIALCODE_METRICS";
	public static String DIAL_CODE_INDEX = "dialcode";
	public static String DIAL_CODE_METRICS_INDEX = "dialcodemetrics";
	public static final String DIAL_CODE_INDEX_TYPE = "dc";
	public static final String DIAL_CODE_METRICS_INDEX_TYPE = "dcm";
	public static final String SEARCH_OPERATION_NOT_IN_OPERATOR = "notIn";
	public static final String SEARCH_OPERATION_NOT_IN = "NT_IN";
	public static final String SEARCH_OPERATION_CONTAINS_OPERATOR = "contains";
	public static final String SEARCH_OPERATION_AND_TEXT_LOWERCASE = "and";
	public static final String SEARCH_OPERATION_AND_OPERATOR = "&";
	public static String EXPERIMENT_SEARCH_INDEX = Platform.config.hasPath("experiment.index.name") ? Platform.config.getString("experiment.index.name"): "experimentsearch";
}
