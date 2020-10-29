create table event (
  id uuid not null,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  data varchar(255).
  primary key (id)
)