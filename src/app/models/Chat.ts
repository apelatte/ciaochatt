import { Message } from "./Message";
import { User } from "./User";

export class Chat{
    id!: number;
    last_update!: Date;
    friend!: User;
    messages!: Array<Message>;
    newMessages!: Array<Message>;
}