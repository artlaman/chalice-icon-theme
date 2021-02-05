open Belt.Array;

type ki =
  | SetId
  | ApiUrl
  | Version
  | SetHttpDriver
  | OnLoad
  | Dry
  | IframeMode;

exception ConfigNotFound(ki);
