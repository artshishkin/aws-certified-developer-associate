CREATE EXTERNAL TABLE IF NOT EXISTS library_db.library (
  `code` string,
  `score` string,
  `true_false` string,
  `name` string,
  `description` string,
  `group` string,
  `type` string,
  `note1` string,
  `note2` string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
WITH SERDEPROPERTIES (
  'serialization.format' = ';',
  'field.delim' = ';'
) LOCATION 's3://art-kate-library/csv/'
TBLPROPERTIES ('has_encrypted_data'='false');